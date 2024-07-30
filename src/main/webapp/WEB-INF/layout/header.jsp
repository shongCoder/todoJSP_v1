<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<style>
    h1 {
        text-align: center;
        margin: 5vh 0 10vh 0;
        color: #0d6efd;
        font-weight: 600;
    }

    header {
        margin-bottom: 10vh;
    }
    .logo {
        color: #0d6efd;
        font-weight: 800;
        font-size: 1.5rem;
    }
    .navbar {
        height: 10vh;
    }
</style>
<header>
    <nav class="navbar bg-body-tertiary">
        <div style="display:grid; grid-template-columns: auto auto; width: 90vw; margin: 0 auto">
            <div class="logo"><a href="/todo/list">Welcom TODO</a></div>
            <div class="container" style="justify-self: end;">
                <div class="row">
                    <div class="col">
                        HOME
                    </div>
                    <div class="col">
                        TODO
                    </div>
                    <div class="col">
                        CONTENTS
                    </div>
                    <div class="col">
                        CONTACT
                    </div>
                </div>
            </div>
        </div>

    </nav>
<%--    <div class="text-success">--%>
<%--        <hr>--%>
<%--    </div>--%>
</header>

