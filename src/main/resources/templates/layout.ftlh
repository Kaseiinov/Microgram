<#import "spring.ftl" as spring>
<#macro layout>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Microgram</title>
    <link rel="stylesheet" href="../static/css/layout.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<#assign known = SPRING_SECURITY_CONTEXT?? >
<#if known>
    <#assign
    user = SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    >
</#if>
<div class="my-container">
    <aside class="sidebar position-fixed top-0 left-0 vh-100 bg-dark text-white p-3 ">
        <div class="logo">Microgram</div>
        <nav class="menu">

            <a href="/"><i class="fas fa-home"></i> Home</a>
            <a href="/user/search"><i class="fas fa-search"></i> Search</a>
            <a href="#"><i class="fas fa-compass"></i> Explore</a>
            <a href="/file/upload"><i class="fas fa-plus-square"></i> Create</a>
            <a href="/user/profile?name="><img src="" class="profile-pic"> Profile</a>
        </nav>
    </aside>




    <#nested spring>

</div>
</body>
</html>
</#macro>