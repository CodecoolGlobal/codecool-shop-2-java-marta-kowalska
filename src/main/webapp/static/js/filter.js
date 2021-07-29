const filter = {
    PRODUCT_TEMPLATE: "",

    init () {
        let productTemplate = document.querySelector("#product-template")
        this.PRODUCT_TEMPLATE = productTemplate.outerHTML;
        productTemplate.parentElement.removeChild(productTemplate);

        this.setEventListenersForFilters();
    },

    setEventListenersForFilters() {

        const categories = document.querySelectorAll("input[type=checkbox][name=category]");
        const suppliers =  document.querySelectorAll("input[type=checkbox][name=supplier]");

        let data = {
            category: [],
            supplier: []
        }

        categories.forEach(function(checkbox) {
            checkbox.addEventListener('change', function() {
                data.category = filter.getCheckedBoxesId(categories)
                filter.showFilteredProducts(data)

            })
        })

        suppliers.forEach(function(checkbox) {
            checkbox.addEventListener('change', function() {
                data.supplier = filter.getCheckedBoxesId(suppliers)
                filter.showFilteredProducts(data)

            })
        })

    },

    createQueryString(obj){
        let esc = encodeURIComponent;
        return Object.keys(obj)
            .map(k => esc(k) + '=' + esc(obj[k]))
            .join('&');
    },

    fetchData(url, callback) {
        fetch(url)
            .then((response) => response.json())
            .then((data) => callback(data))
    },

    getCheckedBoxesId (boxes) {
        return Array.from(boxes) // Convert checkboxes to an array to use filter and map.
            .filter(i => i.checked) // Use Array.filter to remove unchecked checkboxes.
            .map(i => i.value) // Use Array.map to extract only the checkbox values from the array of objects.
    },


    showFilteredProducts(data) {
        let queryStr = filter.createQueryString(data)
        filter.fetchData(`/filter/?${queryStr}`, filter.buildProductView)
    },

    buildProductView(data){
        const productContainer = document.querySelector("#product-container")
        productContainer.innerHTML = ""
        data.forEach(product => {
            let productBox = filter.PRODUCT_TEMPLATE
                .replace("IMAGE", product.image)
                .replace("NAME", product.name)
                .replace("DESCRIPTION", product.description)
                .replace("PRICE", product.price)
                .replace("ID", product.id)
                .replace("id=\"product-container\"", "");
            productContainer.innerHTML += productBox
        })

    }

}

filter.init();