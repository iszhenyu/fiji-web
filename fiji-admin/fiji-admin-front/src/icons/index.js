import Vue from 'vue'
import SvgIcon from '@/components/SvgIcon'  // svg组件

// 全局注册svg-icon
Vue.component('svg-icon', SvgIcon)

/**
 * vue-cli默认情况下会使用 url-loader 对svg进行处理,会将它放在/img 目录下，
 * 所以这时候我们引入svg-sprite-loader 会引发一些冲突。
 * 最简单的就是你可以将 test 的 svg 去掉
 * 最安全合理的做法是使用 webpack 的 exclude 和 include ，
 * 让svg-sprite-loader只处理你指定文件夹下面的 svg，
 * url-loaer只处理除此文件夹之外的所以 svg
 *
 */
const requireAll = requireContext => requireContext.keys().map(requireContext)
const req = require.context('./svg', false, /\.svg$/)
requireAll(req)
