local M = {}

function describe(path)
    vim.notify("describe_tests has been called")
    return nil
end

function M.describe_tests()
    describe("tester")
end

return M
