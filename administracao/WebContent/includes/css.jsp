<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!--
    IE8 support, see AngularJS Internet Explorer Compatibility http://docs.angularjs.org/guide/ie
    For Firefox 3.6, you will also need to include jQuery and ECMAScript 5 shim
  -->
  <!--[if lt IE 9]>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/es5-shim/2.2.0/es5-shim.js"></script>
    <script>
      document.createElement('ui-select');
      document.createElement('match');
      document.createElement('choices');
    </script>
  <![endif]-->

<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="resources/css/uniform.css" />
<link rel="stylesheet" href="resources/css/select2.css" />
<link rel="stylesheet" href="resources/css/matrix-style.css" />
<link rel="stylesheet" href="resources/css/matrix-media.css" />
<link href="resources/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

<script src="resources/ui-select/angular.js"></script>
<script src="resources/ui-select/angular-sanitize.js"></script>
<script src="resources/select.js"></script>
<link rel="stylesheet" href="resources/select.css">

<link rel="stylesheet" href="resources/ui-select/select2.css">
<link rel="stylesheet" href="resources/ui-select/selectize.default.css">

<script src="resources/pedido.js"></script>


