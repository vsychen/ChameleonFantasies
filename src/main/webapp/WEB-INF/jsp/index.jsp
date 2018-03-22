<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Página inicial do projeto Chameleon Fantasies.">
<meta name="author" content="Victor Chen">

<title>Chameleon Fantasies - Minimum Valuable Product Page</title>

<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- W3.CSS by W3Schools -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- Responsive Navbar CSS by Mostafa Omar -->
<link rel="stylesheet"
	href="https://cdn.rawgit.com/vsychen/dependencies/b5634ce9/Chameleon%20Fantasies/css/navbar.css">
</head>

<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>

	<div id="principal" class="w3-display-container"
		style="width: 100vw; height: 90vh; margin-top: 10vh; overflow: hidden">
		<img
			src="https://cdn.rawgit.com/vsychen/dependencies/b5634ce9/Chameleon%20Fantasies/img/background.jpg"
			class="w3-image" />

		<div class="w3-display-topleft w3-container">
			<h1 style="color: #eaea00">Chameleon Fantasies</h1>
		</div>

		<div class="w3-display-bottomright w3-container">
			<a
				style="color: #ffffff; padding: 4px 6px; font-family: -apple-system, BlinkMacSystemFont,&amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; San Francisco&amp;amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; , &amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; Helvetica Neue&amp;amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; , Helvetica , Ubuntu, Roboto, Noto, &amp;amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; Segoe UI&amp;amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; , Arial , sans-serif; font-size: 12px; font-weight: bold; line-height: 1.2; display: inline-block; border-radius: 3px;"
				href="https://unsplash.com/@thomashafeneth" target="_blank"
				rel="noopener noreferrer"><span
				style="display: inline-block; padding: 2px 3px;"><svg
						xmlns="http://www.w3.org/2000/svg"
						style="height: 12px; width: auto; position: relative; vertical-align: middle; top: -1px; fill: white;"
						viewBox="0 0 32 32">
						<title></title><path
							d="M20.8 18.1c0 2.7-2.2 4.8-4.8 4.8s-4.8-2.1-4.8-4.8c0-2.7 2.2-4.8 4.8-4.8 2.7.1 4.8 2.2 4.8 4.8zm11.2-7.4v14.9c0 2.3-1.9 4.3-4.3 4.3h-23.4c-2.4 0-4.3-1.9-4.3-4.3v-15c0-2.3 1.9-4.3 4.3-4.3h3.7l.8-2.3c.4-1.1 1.7-2 2.9-2h8.6c1.2 0 2.5.9 2.9 2l.8 2.4h3.7c2.4 0 4.3 1.9 4.3 4.3zm-8.6 7.5c0-4.1-3.3-7.5-7.5-7.5-4.1 0-7.5 3.4-7.5 7.5s3.3 7.5 7.5 7.5c4.2-.1 7.5-3.4 7.5-7.5z"></path></svg></span><span
				style="display: inline-block; padding: 2px 3px;">Thomas
					Hafeneth</span></a>
		</div>
	</div>

	<!-- JQuery JS -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"
		type="text/javascript"></script>
</body>
</html>