# Cookbook Name:: deploy
# Recipe:: default

node[:monsoon][:applications].each do |app|
  log "Found application with id #{app[:id]}" do level :debug end 
  
  app[:components].each do |component|
  	log "Found component with id #{component[:id]}"	do level :debug end
  	
  	# Check the current node for which server_roles it supports
  	(component[:server_roles] & node.run_list.roles).each do |app_role|
      log "This node supports #{app_role}" do level :debug end
  	  
  	  # Check which recipes this application wants to apply
      component["type"][app_role].each do |thing|
  		log "Deploying to #{thing}"	do level :debug end
  	      
  		# Store current application as a runstate property
  		node.run_state[:current_app] = component
  		# Include specific deploy recipes
  		include_recipe "deploy::#{thing}"
  	  end	  
  	end
  end
	
end

# remove the runstate property again
node.run_state.delete(:current_app)
# used by multiple ljs deployment 
node.run_state.delete(:current_app_redeployed)	
