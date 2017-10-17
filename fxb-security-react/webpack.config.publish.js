/**
 * @date 2017-02-03 11:46:07
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 */
const merge = require('webpack-merge');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const config = require('./webpack.config');
const pkg = require('./package.json');
const { output } = pkg.config;

module.exports = merge(config, {
  plugins: [
    new CleanWebpackPlugin([output]),
  ],
});
