(function (){
    "use strict"; 
    
    // Criando o módulo (aplicação)
    angular.module('taskList-app', []);

    // Criando o controller, acessando o módulo criado
    angular.module('taskList-app')
        .controller('taskListCtrl', taskListController);
    
    taskListController.$inject = ['$scope']; // Injetando dependência ao controller, nesse caso o $scope

    function taskListController($scope) {
        var ctrl = this;

        ctrl.tasks = [
            { text: 'Estudar AngularJS', feito: true },
            { text: 'Fazer uma aplicação em AngularJS', feito: false }
        ];

        ctrl.remaining = remaining;
        ctrl.addTask = addTask;
        ctrl.archive = archive;

        function remaining() {
            var count = 0;
            angular.forEach(ctrl.tasks, function (task) {
                if(!task.feito) count++;
            });
            return count;
        };

        function addTask() {
            ctrl.tasks.push({text: ctrl.taskText, feito: false});
            ctrl.taskText = "";
        }

        function archive() {
            // Utilizando o forEach
            /*var oldTasks = ctrl.tasks;
            ctrl.tasks = [];
            angular.forEach(oldTasks, function (task) {
                if(!task.feito) ctrl.tasks.push(task);
            });*/

            // Usando o método filter, que retorna um array.
            // Aqui irá filtrar as tarefas que NÃO foram feitas e adicionar ao array "tasks".
            ctrl.tasks = ctrl.tasks.filter( (task) => {return !task.feito;} );
        }
    }
})();