import {HomepageController} from "./homepage-controller";

declare const angular;

export default angular.module('ecowheelsApp.controllers', [])
    .controller('homepageCtrl', HomepageController)
    .name
