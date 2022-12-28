const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
    homePage: path.resolve(__dirname, 'src', 'pages', 'homePage.js'),
    getFreelancer: path.resolve(__dirname, 'src', 'pages', 'getFreelancer.js'),
    updateFreelancer: path.resolve(__dirname, 'src', 'pages', 'updateFreelancer.js'),
    addFreelancer: path.resolve(__dirname, 'src', 'pages', 'addFreelancer.js'),
    deleteFreelancer: path.resolve(__dirname, 'src', 'pages', 'deleteFreelancer.js')
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
    openPage: 'http://localhost:8080',
    // diableHostChecks, otherwise we get an error about headers and the page won't render
    disableHostCheck: true,
    contentBase: 'packaging_additional_published_artifacts',
    // overlay shows a full-screen overlay in the browser when there are compiler errors or warnings
    overlay: true,
    proxy: [
      {
        context: [
          '/freelancers'
        ],
        target: 'http://localhost:5001'
      }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html',
      filename: 'index.html',
      inject: false
    }),
    new HtmlWebpackPlugin({
      template: './src/getFreelancer.html',
      filename: 'getFreelancer.html',
      inject: false
    }),
    new HtmlWebpackPlugin({
      template: './src/updateFreelancer.html',
      filename: 'updateFreelancer.html',
      inject: false
    }),
    new HtmlWebpackPlugin({
      template: './src/addFreelancer.html',
      filename: 'addFreelancer.html',
      inject: false
    }),
    new HtmlWebpackPlugin({
      template: './src/deleteFreelancer.html',
      filename: 'deleteFreelancer.html',
      inject: false
    }),
    new CopyPlugin({
      patterns: [
        {
          from: path.resolve('src/css'),
          to: path.resolve("dist/css")
        }
      ]
    }),
    new CleanWebpackPlugin()
  ]
}
