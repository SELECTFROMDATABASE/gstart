const UglifyJSPlugin = require('uglifyjs-webpack-plugin');
module.exports = {
    entry:  __dirname + "\\src\\gstart.js",//已多次提及的唯一入口文件
    output: {
        path: __dirname + "\\public",//打包后的文件存放的地方
        filename: "gstart.js"//打包后输出文件的文件名
    },
    externals : {
        jquery : "window.$"
    },
    plugins:[
        new UglifyJSPlugin({
            uglifyOptions:{
                compress: {ie8: false},
                output: {ie8: false},
                mangle: {
                    ie8: false
                },
                ie8: true
            }
        })
    ]
}