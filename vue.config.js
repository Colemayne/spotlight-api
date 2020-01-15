module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  lintOnSave:false,
  devServer: {
    proxy: {
      '/api/group/v1': {
         target: 'http://localhost:8082',
         changeOrigin: true
       },
       '/api/application/v1': {
         target: 'http://localhost:8082',
         changeOrigin: true
       },
       '/api/endpoint/v1': {
         target: 'http://localhost:8082',
         changeOrigin: true
       }
    }
  }
}