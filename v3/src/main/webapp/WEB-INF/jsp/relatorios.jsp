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

<title>Chameleon Fantasies - Relatórios</title>

<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- W3.CSS by W3Schools -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- Responsive Navbar CSS by Mostafa Omar -->
<link rel="stylesheet"
	href="https://cdn.rawgit.com/vsychen/dependencies/b5634ce9/Chameleon%20Fantasies/css/navbar.css">
</head>

<body
	background="https://cdn.rawgit.com/vsychen/dependencies/b5634ce9/Chameleon%20Fantasies/img/background.jpg">
	<%@ include file="header.jsp"%>

	<div id="principal"
		style="width: 100vw; height: 100vh; overflow: hidden">

		<div
			style="width: 90%; height: 94%; margin-left: 5%; background-color: #ffffff">
			<h1
				style="padding: 50px 0px 0px 0px; color: #000000; text-align: center">Relatórios</h1>

			<div style="text-align: center">
				<button onclick="location.replace('/cf/relatorios/cliente')"
					style="margin: 0px 100px">Cliente</button>
				<button onclick="location.replace('/cf/relatorios/fantasia')"
					style="margin: 0px 100px">Fantasia</button>
				<button onclick="location.replace('/cf/relatorios/funcionario')"
					style="margin: 0px 100px">Funcionário</button>
			</div>

			<div
				style="height: 72%; margin-top: 10px; overflow-x: hidden; overflow-y: auto">
				<c:forEach items="${items}" var="item">
					<c:choose>
						<c:when
							test="${item['class'].name == 'br.com.cf.domain.pojos.ClientePOJO'}">
							<div style="padding: 5px 20px">
								<table style="width: 100%" border="1">
									<col width="100">
									<col width="200">
									<tr>
										<td>Nome</td>
										<td>${item.nome}</td>
									</tr>
									<tr>
										<td>Cpf</td>
										<td>${item.cpf}</td>
									</tr>
									<tr>
										<td>Gastos</td>
										<td>${item.gastos}</td>
									</tr>
								</table>
							</div>
						</c:when>
						<c:when
							test="${item['class'].name == 'br.com.cf.domain.pojos.FuncionarioPOJO'}">
							<div style="padding: 5px 20px">
								<table style="width: 100%" border="1">
									<col width="100">
									<col width="200">
									<tr>
										<td>Nome</td>
										<td>${item.nome}</td>
									</tr>
									<tr>
										<td>Cpf</td>
										<td>${item.cpf}</td>
									</tr>
									<tr>
										<td>Email</td>
										<td>${item.email}</td>
									</tr>
									<tr>
										<td>Telefone</td>
										<td>${item.telefone}</td>
									</tr>
									<tr>
										<td>Cargo</td>
										<td>${item.cargo}</td>
									</tr>
									<tr>
										<td>Salário</td>
										<td>${item.salario}</td>
									</tr>
								</table>
							</div>
						</c:when>
						<c:otherwise>
							<div style="padding: 5px 20px">
								<table style="width: 100%" border="1">
									<col width="100">
									<col width="200">
									<tr>
										<td>Nome</td>
										<td>${item.nome}</td>
									</tr>
									<tr>
										<td>Codigo</td>
										<td>${item.codigo}</td>
									</tr>
									<tr>
										<td>Quantidade</td>
										<td>${item.quantidade}</td>
									</tr>
									<tr>
										<td>Preço de Compra</td>
										<td>${item.precoCompra}</td>
									</tr>
									<tr>
										<td>Preço de Venda</td>
										<td>${item.precoVenda}</td>
									</tr>
									<tr>
										<td rowspan="5">Partes da Fantasia</td>
										<td>${not empty item.partes[0].descricao?item.partes[0].descricao:'Sem chapeu'}</td>
									</tr>
									<tr>
										<td>${not empty item.partes[1].descricao?item.partes[1].descricao:'Sem parte de cima'}</td>
									</tr>
									<tr>
										<td>${not empty item.partes[2].descricao?item.partes[2].descricao:'Sem parte de baixo'}</td>
									</tr>
									<tr>
										<td>${not empty item.partes[3].descricao?item.partes[3].descricao:'Sem braços'}</td>
									</tr>
									<tr>
										<td>${not empty item.partes[4].descricao?item.partes[4].descricao:'Sem sapatos'}</td>
									</tr>
								</table>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
		<div class="w3-display-bottomright w3-container">
			<a
				style="color: #ffffff; padding: 4px 6px; font-family: -apple-system, BlinkMacSystemFont,&amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; San Francisco&amp;amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; , &amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; Helvetica Neue&amp;amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; , Helvetica , Ubuntu, Roboto, Noto, &amp;amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; Segoe UI&amp;amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; amp; quot; , Arial , sans-serif; font-size: 12px; font-weight: bold; line-height: 1.2; display: inline-block; border-radius: 3px;"
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