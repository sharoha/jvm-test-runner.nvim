local M = {}

function M.find_tests()
    vim.notify("Find tests has been called")
end

function M.refresh()
    vim.notify("Refresh test has been called")
end

function M.run_current_test()
    vim.notify("Run current test has been called")
end
return M
