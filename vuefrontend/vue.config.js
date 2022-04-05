module.exports = {
  outputDir: "../docs",
  publicPath: "/ojt0330/",

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
