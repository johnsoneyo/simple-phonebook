/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var contactApp = angular.module('contactApp', ['ngRoute']);



contactApp.controller("contactListCtrl", ['$scope', '$http', function ($scope, $http) {

        var res = $http.get("/getContacts");
        res.success(function (data, status, headers, config) {
            console.log('how far');
            $scope.contactList = data;
        });


    }]);


contactApp.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;
                element.bind('change', function () {
                    scope.$apply(function () {
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

contactApp.service('fileUpload', ['$http', function ($http) {
        this.uploadFileToUrl = function (file, contact, uploadUrl) {
            var fd = new FormData();
            fd.append('file', file);
            fd.append('person', JSON.stringify(contact));

            return $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })

        }
    }]);

contactApp.controller('createContactCtrl', ['$scope', 'fileUpload', function ($scope, fileUpload) {

        $scope.iscreated = true;

        $scope.createContact = function (contact) {
            var file = $scope.myFile;
            var uploadUrl = "/saveContact";

            fileUpload.uploadFileToUrl(file, contact, uploadUrl).success(function (response) {
                $scope.message = "Contact Created Successfully";
                $scope.iscreated = false;
                $scope.contact = {};
            });

        };
    }]);

// contactApp.controller("createContactCtrl", ['$scope', '$http', '$window', function ($scope, $http, $window) {
//
//        $scope.iscreated = true;
//         
//        $scope.createContact = function (contact) {
//            
//         
//            var res = $http.post("/saveContact", contact);
//            res.success(function (data, status, headers, config) {
//                $scope.iscreated = false;
//                $scope.message = "Contact Created Successfully";
//                var url = "addcontact.html";
//               // $window.location.href = url;
//            });
//        }
//
//    }]);
