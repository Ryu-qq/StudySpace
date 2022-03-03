const path = require('path');
const MyWebpackPlugin = require('./my-webpack-plugin');

module.exports ={
    mode:'development',
    entry:{
        main:'./src/app.js'

    },
    output:{
        path: path.resolve('./dist/'),
        filename: '[name].js'
    },
    module:{
        rules: [
            {   //확장자 를 가진 확장자는 모두 로더로 돌리겠다
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            },
            {   //확장자 를 가진 확장자는 모두 로더로 돌리겠다
                test: /\.(png|jpg|gif|svg$)/,
                loader: 'url-loader',
                options:{
                    publicPath: './dist/',
                    name: '[name].[ext]?[hash]',
                    limit: 20000, //2kb
                },
            }
        ]
    },
    plugins:[
        new MyWebpackPlugin(),
    ]
}