// @RestController
// @RequestMapping("/companies")
// public class PeopleController {

//     @Autowired
//     private CompanyRepository companyRepository;

//     @GetMapping(path = "/{id}")
//     public Company getCompany(@PathVariable long id) {
//         // ищем сущность в базе по её id
//         return this.companyRepository.findById(id);
//     }

//     @GetMapping(path = "")
//     public Iterable<Company> getPeople() {
//         // получаем все сущности из базы
//         return this.companyRepository.findAll();
//     }

//     // BEGIN

//     // END
// }
