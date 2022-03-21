

class MyWebpackPlugin {
    apply(compiler) {

        compiler.hooks.emit.tapAsync("My Plugin", (compilation, callback) => {
           compilation.assets['main.js'].source = () =>{
                const banner =[
                    '/**',
                    '/이것은 플러그인이 처리한 결과입니다'
                ].join('\n');
                return banner + '\n\n' + source;
            }

            callback();
        });
    }
}

module.exports = MyWebpackPlugin;