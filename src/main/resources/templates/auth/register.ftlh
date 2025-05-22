<#import "/spring.ftl" as spring>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="my-container">
    <div class="form-section">
        <div class="form-box">
            <h1>Microgram</h1>
            <form action="/auth/register" method="post">

                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <@spring.formInput "userDto.email" 'id="form3Example1cg" class="form-inputs" placeholder="Email"' />
                <@spring.showErrors "<br>" "error text-danger" />

                <@spring.formInput "userDto.password" 'id="form3Example1cg" class="form-inputs" placeholder="Password"' />
                <@spring.showErrors "<br>" "error text-danger" />

                <@spring.formInput "userDto.firstName" 'id="form3Example1cg" class="form-inputs" placeholder="First name"' />
                <@spring.showErrors "<br>" "error text-danger" />

                <@spring.formInput "userDto.lastName" 'id="form3Example1cg" class="form-inputs" placeholder="Last name"' />
                <@spring.showErrors "<br>" "error text-danger" />

                <button>Sign up</button>
            </form>
        </div>
        <div class="signup-box">
            <p>Do you have an account? <a href="/auth/login">Sign in</a></p>
        </div>
    </div>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: sans-serif;
        }

        body {
            background-color: #fafafa;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .my-container {
            display: flex;
            align-items: center;
            gap: 40px;
        }

        .phone-image img {
            max-height: 600px;
        }

        .form-section {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        .form-box {
            background-color: #fff;
            border: 1px solid #dbdbdb;
            padding: 40px;
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 350px;
        }

        .form-box h1 {
            font-family: 'Billabong', cursive;
            font-size: 48px;
            margin-bottom: 20px;
        }



        .form-inputs{
            width: 100%;
            padding: 9px;
            margin-bottom: 10px;
            border: 1px solid #dbdbdb;
            border-radius: 3px;
            background-color: #fafafa;
        }

        button {
            background-color: #3897f0;
            color: white;
            width: 100%;
            padding: 9px;
            border: none;
            border-radius: 4px;
            font-weight: bold;
            cursor: pointer;
            margin-top: 10px;
        }

        .signup-box {
            background-color: #fff;
            border: 1px solid #dbdbdb;
            padding: 20px;
            text-align: center;
        }

        .signup-box a {
            color: #3897f0;
            text-decoration: none;
        }

    </style>
</div>
</body>
</html>
