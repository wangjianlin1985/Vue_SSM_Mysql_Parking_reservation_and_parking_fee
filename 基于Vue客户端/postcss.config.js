module.exports = {
  plugins: {
    autoprefixer: {},
    "postcss-px-to-viewport": { //iPhone6 750 x 1334
      viewportWidth: 375, //视窗的宽度，对应的是设计稿的宽度
      viewportHeight: 667, //视窗的高度，对应的是设计稿的高度（也可以不配置）
      unitPrecision: 5, //指定'px'转换为vw时保留 5 位小数
      viewportUnit: 'vw', //指定转换单位，一般使用 vw
      selectorBlackList: ['.ignore'], //指定不需要转换的类名 《 ignore 》 另外 使用 ignore-xxx 的类也会忽略
      minPixelValue: 1, //最小转换单位， 小于等于 1px 的就不进行转换
      mediaQuery: false, //允许媒体查询转换 px
      //exclude: [/TabBar/] //排除文件，使用正则表达式
    }
  }
}