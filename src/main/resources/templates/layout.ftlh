<#import "spring.ftl" as spring>
<#macro layout>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Microgram</title>
    <link rel="stylesheet" href="../static/css/layout.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <aside class="sidebar">
        <div class="logo">Microgram</div>
        <nav class="menu">
            <a href="/"><i class="fas fa-home"></i> Home</a>
            <a href="#"><i class="fas fa-search"></i> Search</a>
            <a href="#"><i class="fas fa-compass"></i> Explore</a>
            <a href="#"><i class="fas fa-plus-square"></i> Create</a>
            <a href="#"><img src="" class="profile-pic"> Profile</a>
        </nav>
    </aside>


    <#assign known = SPRING_SECURITY_CONTEXT?? >
    <#if known>
        <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        >
    </#if>

    <#nested spring>

</div>
</body>
</html>
</#macro>