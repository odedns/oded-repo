# Cookbook Name:: deploy
# Recipe:: tomcat6

# create  dir for php webapp.
php_path = "#{node[:deploy][:webapps_path]}"

directory "#{php_path}"  do
  owner "root"
  group "root"
  mode "0777"
  action :create
  recursive true
end


app = node.run_state[:current_app] 	

# determine the version key by checking the landscape attribute first
# and then defaulting back to the evironment if not set 
versionkey = node[:barkeeper][:landscape]  
if(!versionkey) then
  versionkey = node[:environment]
end

version = app[:version][versionkey]
log "Version key from env/landscape : #{versionkey}"
  
#version = "latest" #untill fixing it . was before: version = app[:version][versionkey] 


# if no version can be found for the key (could be landscape is using the environment default)
if(!version) then
  version = app[:version][node[:environment]]
end  
  
  
if (version == "latest") then 
  # Making a remote call to the bamboo server to find the latest successful build
  bambooServiseUrl = URI.parse("http://build.wdf.sap.corp:8080/bamboo/rest/api/latest/result/#{app[:bamboo_id]}.json?os_authType=basic&os_username=bamboo-api-user&os_password=monsoon");
  log "bambooServiseUrl: #{bambooServiseUrl} "
  bambooResult = Net::HTTP.get(bambooServiseUrl)
  version      = JSON.parse(bambooResult)["results"]["result"].find{|item| item["state"] == "Successful"}["number"]
end

log "Version to be deployed : #{version}"

  
release_url      = eval %Q{"#{node[:deploy][:repository_assembly_url]}"}
log "release_url - #{release_url}" 
log "node_deploy = node[:deploy][:base_dir]  app_module = #{app[:module]} version - #{version} app[:id] = #{app[:id]}" 
release_path     = File.join(node[:deploy][:base_dir], app[:id], "#{app[:module]}-#{version}")
log "release_path = #{release_path}"

# Download Artifacts if needed
if !File.directory? release_path then
  log "Downloading release #{release_url}. Hang on. This might take a while..."
  
   directory release_path do
     action :create
     mode 0755
     recursive true    
     owner "root"
     group "root"
   end   
   
 log "#{release_url}"
						
 bash "download_release_zip" do
  user "root"
  cwd release_path
  code <<-EOH
  wget #{release_url} --no-proxy  
 EOH
  end	
  
  bash "unpack_release" do
      user "root"
      cwd release_path
      code <<-EOH
      tar xvfz #{app[:module]}.tgz
      EOH
  end 
    
  bash "remove_tmpfiles" do
     user "root"
     cwd release_path
     code <<-EOH
     rm #{app[:module]}.tgz
     EOH
   end 
end

# Remove the extracted release file directories
# always leave the last two releases for
# potentially desaster scenario
bash "remove_tmpdirectories" do
  user "root"
  cwd File.join(node[:deploy][:base_dir], app[:id])
  code <<-EOH
   rm -irf `ls -t  | awk 'NR>2'`
  EOH
end 

deploy_version = "#{node[:lamp][:deployed_release]}"
if deploy_version.to_s != version.to_s then
  
      log ""
      log "< Deploying #{release_path} >"
      log "       \\   ^__^"
      log "        \\  (oo)\\_______"
      log "            (__)\\       )\\/\\"
      log "                 ||----w |"
      log "                 ||     ||"
      log ""
        
      
      log "now copying php files..."
      bash "install php files" do
        user "root"
        cwd php_path
        code <<-EOH
          rm -rf *
          tar xvfz #{release_path}/*.tgz
          unzip #{release_path}/*.zip
        EOH
      end
else
  log "version is up to date - not installing"  
end 
# if 


node.set[:lamp][:deployed_release] = "#{version}"
node.set[:lamp][:deployed_bamboo_id] = "#{app[:bamboo_id]}"
 
log "deploy php done"
