<%@include file="../layout/header.jsp"%>
<style>
    .card {
        width: 90vw;
        margin: 0 auto;
        padding: 2em;
        background: #ECEFF3;
    }
    h2 {
        text-align: center;
        padding-bottom: 2em;
    }
    .make_todo {
        display: flex;
        width: 100%;
    }
    button{
        margin-left: auto;
    }
</style>
<div class="card">
    <h2>Make Todo</h2>
    <div class="car-body">
        <form action="/todo/register" method="post">
            <div class="mb-3">
                <label class="form-label">Title</label>
                <input type="text" name="title" class="form-control" placeholder="Please write title">
            </div>
            <div class="mb-3">
                <label class="form-label">Writer</label>
                <input type="text" name="writer" class="form-control" placeholder="Please write writer">
            </div>
            <div class="make_todo">
                <button type="submit" class="btn btn-primary">SAVE</button>
            </div>
        </form>
    </div>
</div>

<%@include file="../layout/footer.jsp"%>