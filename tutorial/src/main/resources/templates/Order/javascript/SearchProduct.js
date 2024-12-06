$(document).ready(function() {
    // Xử lý tìm kiếm sản phẩm
    $("#formSearch").submit(function(e) {
        e.preventDefault();
        var action = $(this).attr("action");
        var method = $(this).attr("method");
        var searchValue = $(this).find("input[name='searchValue']").val();

        $.ajax({
            url: action,
            type: method,
            data: { searchValue: searchValue },
            success: function(data) {
                $("#searchResult").html($(data).find("#searchResult > *"));
            },
            error: function() {
                alert("Có lỗi xảy ra trong quá trình tìm kiếm.");
            }
        });
    });

    // Xử lý thêm sản phẩm vào giỏ hàng
    $(document).on("click", ".add-to-cart-btn", function(e) {
        e.preventDefault();
        var productId = $(this).data("product-id");

        $.ajax({
            url: "/Order/AddToCart",
            type: "POST",
            data: { productId: productId },
            success: function(data) {
                $("#shoppingCart").html($(data).find("#shoppingCart > *"));
            },
            error: function() {
                alert("Có lỗi xảy ra trong quá trình thêm sản phẩm vào giỏ hàng.");
            }
        });
    });

    // Tải giỏ hàng khi tải trang
    loadShoppingCart();
});

function loadShoppingCart() {
    $.ajax({
        url: "/Order/ShowShoppingCart",
        type: "GET",
        success: function(data) {
            $("#shoppingCart").html($(data).find("#shoppingCart > *"));
        },
        error: function() {
            alert("Có lỗi xảy ra trong quá trình tải giỏ hàng.");
        }
    });
}
