module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  devServer: {
    proxy: {
      '/api/manage/v1': {
         target: 'http://localhost:8081',
         changeOrigin: true
       },
       '/api/test/v1': {
         target: 'http://localhost:8081',
         changeOrigin: true
       }
    }
  }
}