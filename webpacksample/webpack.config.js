const path = require('path');

module.exports ={
    mode:'development',
    entry:{
        main:'./src/app.js'

    },
    output:{
        path: path.resolve('./dist/main.js'),
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
                test: /\.png$/,
                loader: 'file-loader',
                options:{
                    publicPath: './dist/main.js',
                    name: '[name].[ext]?[hash]'
                },
            }
        ]
    }
}