define :deploy do
  unless params[:name]
    log "The deploy resource needs to know an id of an entry in the application databag."
    return
  end

  current_app = params[:name]

  unless node[:monsoon][:applications][current_app]
    log "You need to define an attribute node[:monsoon][:applications][:#{current_app}] containing the deploy parameters. See the recipes in the deploy cookbook. Doing nothing!"
    return
  end

  unless node[:monsoon][:applications][current_app][:type]
    log "Your application #{current_app} doesn't include a type attribute. It must be set to the name one of the recipes in the deploy cookbook."
    return
  end

  node.run_state[:current_app] = current_app
  include_recipe "deploy::#{node[:monsoon][:applications][current_app][:type]}"
  node.run_state[:seen_recipes].delete("deploy::#{node[:monsoon][:applications][current_app][:type]}")
  node.run_state.delete(:current_app)
end
