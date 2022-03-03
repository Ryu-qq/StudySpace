class MyWebpackPlugin{
     apply(compiler){
    //     compiler.hooks.done.tap('My plugin', status =>{
    //         console.log('my plugin done');
    //     })

        compiler.plugin('emit', (compilation, callback ) =>{
            const source = compilation.assets['main.js'].source();

            compilation.assets['main.js'].source = () =>{
                const banner =[
                    '/**',
                    '/이것은 플러그인이 처리한 결과입니다'
                ].join('\n');
                return banner + '\n\n' + source;
            }

            callback();
        })  
    }   
}


module.exports = MyWebpackPlugin;