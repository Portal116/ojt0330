module.exports = {
  outputDir: "../docs",
  publicPath: "/ojt0330/",

  devServer: {
    port: 9000,
    proxy: {
      "/api": {
        target: "https://portal116.github.io/ojt0330",
        changeOrigin: true,
        logLevel: "debug",
        secure: false,
      },
    },
  },

  transpileDependencies: ["vuetify"],
  lintOnSave: false,
};
