module.exports = {
  outputDir: "build",

  devServer: {
    port: 9000,
    proxy: {
      "/api": {
        target: "http://localhost:8091",
        changeOrigin: true,
        logLevel: "debug",
        secure: false,
        // pathRewrite: {     "^/api": "" }
      },
    },
  },

  transpileDependencies: ["vuetify"],
  lintOnSave: false,
};
