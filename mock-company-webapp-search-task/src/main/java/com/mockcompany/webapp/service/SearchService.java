public class SearchService {

    private ProductItemRepository productitemRepository;

    public SearchService(ProductItemRepository productitemRepository) {
        this.productitemRepository = productitemRepository;
    }

    @GetMapping("/api/products/search")
    public Collection<ProductItem> search(@RequestParam("query") String query) {
        /*
         * TODO: !!!! Implement this method !!!!
         *  The easiest implementation will be to use the findAll as we are below. Then filter using Java
         *  string methods such as contains(...), toLowerCase(...), equals(...), etc.
         *
         *  The requirements are defined in src/test/groovy/com/mockcompany/webapp/controller/SearchControllerSpec.groovy
         *
         *  Read through the tests to get an idea of how search should work.  When the tests are written before the code,
         *  it is known as Test Driven Development (TDD) and is a common best practice. The Spock framework is a great
         *  framework for TDD because the tests are written very descriptively using sentences.
         *
         *    https://spockframework.org/spock/docs/2.0/spock_primer.html
         *
         *  For an added challenge, update the ProductItemRepository to do the filtering at the database layer :)
         */

        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        boolean exactMatch = false;
        if(query.startswith("\"") && query.endsWith("\"")) {
            exactMatch = true;

            query = query.string(1, query.length-1);
        }  else {
            query = query.LowerCase();
        }


        // This is a loop that the code inside will execute on each of the items from the database.
        for (ProductItem item : allItems) {
            // TODO: Figure out if the item should be returned based on the query parameter!
            boolean nameMatches;
            boolean descMatches;

            if(exactMatch) {
                nameMatches = query.equals(items.getName());

                descMatches = query.equals(items.getDescription());
            } else {
                nameMatches = item.getName().toLowerCase().contains(query);
                descMatches = item.getDescription().toLowerCase().contains(query);
            }

            if(nameMatches || descMatches) {
                itemList.add(item);
            }

        }
        return itemList;
    }
}
}