vim.api.nvim_create_user_command("JvmGetRegisteredTest", function()
	print("called registeredTest command")
end, { desc = "Get a list of registered tests" })

vim.api.nvim_create_user_command("JvmRunCurrentTest", function()
	print("called JvmRunCurrentTest command")
end, { desc = "Execute the test under cursor" })

vim.api.nvim_create_user_command("JvmRefreshTest", function()
	print("called JvimRefreshTestcommand")
end, { desc = "Refresh the list of registered Test" })
