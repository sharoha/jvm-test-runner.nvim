local M = {}

local defaults = {
    search_dir = {
        "src/intTest",
        "src/test",
    },

    annotations = {
        "Test",
        "ParameterizedTest",
    },
}

M.options = {}

function M.setup(opt)
    M.options = vim.tbl_deep_extend("force", vim.deepcopy(defaults), opt or {})
end

print("config set to ", M.options)

return M
