module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        'assets': '@/assets',
        'common': '@/common',
        'components': '@/components',
        'network': '@/network',
        'views': '@/views',
      }
    }
  },
  lintOnSave: false,
  devServer: {
    open: true,
    host: '0.0.0.0',
    port: '8089',
    disableHostCheck: true,
    https: false,
    hotOnly: false,
    //代理
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8080/JavaWebProject',
        //target: 'http://10.96.150.56:3000',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          pathRewrite: {'^/api': '/api'} //重写之后url为 http://192.168.1.16:8085/api/xxxx
        }
      },
      '/images': {
        target: 'http://127.0.0.1:8080/JavaWebProject',
        //target: 'http://10.96.150.56:3000',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          pathRewrite: {'^/images': '/images'} //重写之后url为 http://192.168.1.16:8085/api/xxxx
        }
      },
      '/JavaWebProject': {
        target: 'http://127.0.0.1:8080',
        //target: 'http://10.96.150.56:3000',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          pathRewrite: {'^/JavaWebProject': '/JavaWebProject'} //重写之后url为 http://192.168.1.16:8085/api/xxxx
        }
      },
      '/upload': {
        target: 'http://127.0.0.1:8080/JavaWebProject',
        //target: 'http://10.96.150.56:3000',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          pathRewrite: {'^/upload': '/upload'} //重写之后url为 http://192.168.1.16:8085/api/xxxx
        }
      },
      '/ueditor1_4_3': {
        target: 'http://127.0.0.1:8080/JavaWebProject',
        //target: 'http://10.96.150.56:3000',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          pathRewrite: {'^/ueditor1_4_3': '/ueditor1_4_3'} //重写之后url为 http://192.168.1.16:8085/api/xxxx
        }
      }
    }
  }
}