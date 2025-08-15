package net.engineeringdigest.journalApp.repository;
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public interface JournalEntryRespository extends MongoRepository<JournalEntry,String> {

}
// controller ----->service------>repository
