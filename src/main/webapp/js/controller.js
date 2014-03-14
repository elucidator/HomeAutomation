/**
 * Created by pieter on 3/7/14.
 */
var homeAutomationApp = angular.module('homeAutomationApp', []);

homeAutomationApp.controller('mainController', function ($scope) {
        $scope.data_available = [
            { 'name': 'Weather'},
            { 'name': 'Power'},
            { 'name': 'Gas'}
        ];
    }
);
