(function (){
    "use strict"; 
    
    var divApp1 = document.getElementById('app1');
    var divApp2 = document.getElementById('app2');

    angular.module('app1', [])
        .controller('ctrl1', function ($scope) {
            $scope.nome = 'teste';
        });
    
    angular.module('app2', [])
        .controller('ctrl2', function ($scope) {
            $scope.contador = 0;
        });
    
    angular.bootstrap(divApp1, ['app1']);
    angular.bootstrap(divApp2, ['app2']);
})();