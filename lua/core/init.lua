local config = require("core.config")

local M = {}

function M.setup(opts)
    config.setup(opts)
end

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
