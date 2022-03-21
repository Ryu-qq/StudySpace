module.exports = function myBabelPlugin(){
    return{
        visitor:{
            VariableDeclation(path){
                console.log('VariableDeclaration() kind:'. path.node.kind); // const
                
                if(path.node.kind ==='const'){
                    path.node.kind = 'var'
                }
            },

        },
    };
}