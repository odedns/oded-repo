# Cookbook Name:: deploy
# Recipe:: rails

###
# You really most likely don't want to run this recipe from here - Use the
# deploy definition
###

###
# The deploy recipe expects an attribute hash containing the parameters for
# the rails deploy action. It should look similar to this example:
###
=begin
set[:monsoon][:applications][:expertondemand] = {
  :type => "rails",
  :repository => "git@github.wdf.sap.corp:moorails/expertondemand.git",
  :revision => {
    :production => "production",
    :staging  => "staging"
  },
  :force => {
    :production => true,
    :staging  => true 
  },
  :deploy_to => "/monsoon/webapps/expertondemand", 
  :owner => "nginx", 
  :group => "nginx"
}
=end

app_name = node.run_state[:current_app] 
app      = node[:monsoon][:applications][app_name]
env      = node[:RAILS_ENV]

directory app[:deploy_to] do
  owner app[:owner]
  group app[:group]
  mode '0755'
  recursive true
end

directory "#{app[:deploy_to]}/shared" do
  owner app[:owner]
  group app[:group]
  mode '0755'
  recursive true
end

%w{ log pids system vendor_bundle }.each do |dir|

  directory "#{app[:deploy_to]}/shared/#{dir}" do
    owner app[:owner]
    group app[:group]
    mode '0755'
    recursive true
  end

end

deploy_revision app_name do
  revision app[:revision][env]
  repository app[:repository]
  user app[:owner]
  group app[:group]
  deploy_to app[:deploy_to]
  environment 'RAILS_ENV' => env 
  action app[:force][env] ? :force_deploy : :deploy
  shallow_clone true

  before_migrate do
    link "#{release_path}/vendor/bundle" do
      to "#{app[:deploy_to]}/shared/vendor_bundle"
    end

    common_groups = %w{development test cucumber staging production osx}
    rvm_shell "bundle install (#{env})" do
      ruby_string app[:ruby_string]
      cwd release_path
      code <<-EOH
        gem list
        bundle install --deployment --without #{(common_groups - [env]).join(' ')}
      EOH
    end
  end

  restart_command "touch tmp/restart.txt #{app.inspect}" 

  after_restart do
    message = "< Deployed #{app_name} using #{app[:type]} recipe >"

    Chef::Log.info(" "+"-"*(message.length-2))
    Chef::Log.info("< Deployed #{app_name} using #{app[:type]} recipe >")
    Chef::Log.info(" "+"-"*(message.length-2))       
	  Chef::Log.info("		      \\   ^__^")
		Chef::Log.info("	               \\  (oo)\\_______")
	  Chef::Log.info("		          (__)\\       )\\/\\")
	  Chef::Log.info("		              ||----w |")
	  Chef::Log.info("		              ||     ||") 
  end
end
