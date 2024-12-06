+function() {
    document.addEventListener("DOMContentLoaded",() => {
        const $ = document.querySelector.bind(document);
        const $$ = document.querySelectorAll.bind(document);

        const addToCart = (e) => {
            const formAddToCart = $(".formAddToCart");
            const formData = new FormData(formAddToCart);
            for(let pair of formData.entries()) {
                console.log(`pair[0] : ${pair[0]} - pair[1] : ${pair}`)
            }
        }

    })
}