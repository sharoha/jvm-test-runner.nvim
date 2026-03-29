vim.api.nvim_create_user_command("JvmGetRegisteredTest", function()
    require("jvm-test-runner").find_tests()
end, { desc = "Get a list of registered tests" })

vim.api.nvim_create_user_command("JvmRunCurrentTest", function()
    require("jvm-test-runner").run_current_test()
end, { desc = "Execute the test under cursor" })

vim.api.nvim_create_user_command("JvmRefreshTest", function()
    require("jvm-test-runner").refresh()
end, { desc = "Refresh the list of registered Test" })
