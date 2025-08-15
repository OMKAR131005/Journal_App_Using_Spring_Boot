package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRespository jornalEntryRepo;

    public void saveEntry(JournalEntry journalEntry){
     jornalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return jornalEntryRepo.findAll();
    }
    public JournalEntry findById(String id){
        return jornalEntryRepo.findById(id) .orElse (null);
    }
    public void deleteById(String id){
         jornalEntryRepo.deleteById(id) ;

    }
}
