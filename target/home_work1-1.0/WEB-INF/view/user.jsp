<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/WEB-INF/view/_header.jsp" %>

<header>
    <nav class="navbar navbar-expand-lg navbar-light mynavbar">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Cats</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="user.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="forum.html">Forum</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Useful information</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div class="container-fluid main-body" id="main-body">

    <div class="body-info" id="userinfo">
        <form action="<c:url value="/user"/>" method="post">
            <div class="card" style="width: 18rem;">
                <img src="img\all_avatar.jpg" class="card-img-top img-fluid">
                <div class="card-body">
                    <h5 class="card-title">${username}</h5>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">${email}</li>
                </ul>
                <div class="card-body">
                    <button type="submit" class="btn my-btn" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                        Edit
                    </button>
                    <!-- <button class="btn my-btn" type="submit" name="edit">edit</button> -->
                </div>

            </div>
        </form>


        <div class="container-fluid carusel-container" >
            <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#cat1" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#cat2" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#cat3" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="img\avatar.jpg" class="d-block w-100">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Chember1</h5>
                            <p>Some representative placeholder content for the first slide.</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="img\avatar.jpg" class="d-block w-100">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Chember2</h5>
                            <p>Some representative placeholder content for the second slide.</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="img\avatar.jpg" class="d-block w-100">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Chember3</h5>
                            <p>Some representative placeholder content for the third slide.</p>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>

            </div>
        </div>

        <div class="d-grid gap-2">
            <button class="btn my-btn decorator" type="submit" name="editCat" data-bs-toggle="modal" data-bs-target="#editcat">edit</button>
            <button class="btn my-btn decorator" type="submit" name="addCat" data-bs-toggle="modal" data-bs-target="#addcat">add cat</button>
        </div>

    </div>

<%--    <div class="body-function" id="text">--%>

<%--        <button class="btn my-btn btn-add" type="submit" name="add" id="add-post">Add</button>--%>

<%--        <span class="text-in-main">--%>
<%--                <h2>Your posts</h2>--%>
<%--            </span>--%>

<%--        <div id="recipes">--%>
<%--            <div class="card recipe-card">--%>
<%--                <div class="card-header recipe-title">--%>
<%--                    <h5><a href="post.html">CatFood</a></h5>--%>
<%--                </div>--%>

<%--                <div class="card-body">--%>
<%--                    <p class="card-text">--%>
<%--                    <div class="card-text-wrapper">--%>

<%--                        Cats are obligate carnivores—meaning, they are true carnivores and depend upon the nutrients present--%>
<%--                        in animal flesh for their dietary needs. Even domesticated cats will relish freshly killed meat from rodents,--%>
<%--                        rabbits, amphibians, birds, reptiles and fish, but cats are also opportunistic feeders and will readily take--%>
<%--                        cooked food[10][better source needed] as well as dried cat food when offered, if that food is palatable.--%>
<%--                        The natural diet of cats therefore does not include any vegetable matter,--%>
<%--                        although cats have been known to eat certain plants and grasses occasionally, usually as an emetic.--%>


<%--                    </div>--%>
<%--                    </p>--%>
<%--                    <div class="recipe-description">--%>
<%--                        <p>--%>
<%--                            Tags:  <a href = "#">care</a> <a href = "#">food</a> <a href = "#">diet</a> <a href = "#">catsdiet</a>--%>
<%--                        </p>--%>
<%--                    </div>--%>

<%--                    <div style="display: flex; flex-direction: row-reverse; margin-right: 10px;">--%>
<%--                        <button class="button dark ">--%>
<%--                            <div class="hand">--%>
<%--                                <div class="thumb"></div>--%>
<%--                            </div>--%>
<%--                            <span>100<span>--%>
<%--                        </button>--%>


<%--                    </div>--%>

<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

</div>

<%--    <!-- Modal -->--%>
<%--    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">--%>
<%--        <div class="modal-dialog">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h5 class="modal-title" id="staticBackdropLabel">Edit personal information</h5>--%>
<%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                </div>--%>
<%--                <div class="modal-body" >--%>
<%--                    <input type="text" name="username" placeholder="username"/> <br>--%>
<%--                    <input type="text" name="email" placeholder="email"/>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                    <button type="button" class="btn mybtn-edit">Edit</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="modal fade" id="editcat" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">--%>
<%--        <div class="modal-dialog">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h5 class="modal-title" id="staticBackdropLabel">Edit cat information</h5>--%>
<%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                </div>--%>
<%--                <div class="modal-body" >--%>
<%--                    <input type="text" name="catname" placeholder="catname"/> <br>--%>
<%--                    <input type="text" name="catinfo" placeholder="catinfo"/>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                    <button type="button" class="btn mybtn-edit">Edit</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="modal fade" id="addcat" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">--%>
<%--        <div class="modal-dialog">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h5 class="modal-title" id="staticBackdropLabel">Add cat</h5>--%>
<%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                </div>--%>
<%--                <div class="modal-body" >--%>
<%--                    <input type="text" name="catname" placeholder="catname"/> <br>--%>
<%--                    <input type="text" name="catinfo" placeholder="catinfo"/>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                    <button type="button" class="btn mybtn-edit">Edit</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <script>--%>
<%--        document.querySelectorAll('.button').forEach(button => {--%>

<%--            button.addEventListener('click', e => {--%>
<%--                button.classList.toggle('liked');--%>
<%--                if (button.classList.contains('liked')) {--%>
<%--                    gsap.fromTo(button, {--%>
<%--                        '--hand-rotate': 8--%>
<%--                    }, {--%>
<%--                        ease: 'none',--%>
<%--                        keyframes: [{--%>
<%--                            '--hand-rotate': -45,--%>
<%--                            duration: .16,--%>
<%--                            ease: 'none'--%>
<%--                        }, {--%>
<%--                            '--hand-rotate': 15,--%>
<%--                            duration: .12,--%>
<%--                            ease: 'none'--%>
<%--                        }, {--%>
<%--                            '--hand-rotate': 0,--%>
<%--                            duration: .2,--%>
<%--                            ease: 'none',--%>
<%--                            clearProps: true--%>
<%--                        }]--%>
<%--                    });--%>
<%--                }--%>
<%--            })--%>

<%--        });--%>
<%--    </script>--%>

<%@include file="/WEB-INF/view/_footer.jsp" %>