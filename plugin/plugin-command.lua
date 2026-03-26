local M = {}

function M.setup(opts)
    local group = vim.api.nvim_create_augroup("jvm_test_executor", { clear = true })

end

vim.api.nvim_create_user_command("JvmGetRegisteredTest", function()
    require("core").find_tests()
end, { desc = "Get a list of registered tests" })

vim.api.nvim_create_user_command("JvmRunCurrentTest", function()
    require("core").run_current_test()
end, { desc = "Execute the test under cursor" })

vim.api.nvim_create_user_command("JvmRefreshTest", function()
    require("core").refresh()
end, { desc = "Refresh the list of registered Test" })
