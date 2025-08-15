package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRespository;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
    @RequestMapping("/journal")
    public class JournalEntryController {
     @Autowired

     private JournalEntryService jornalService;
        @GetMapping
        public List<JournalEntry> getAll() {
            return jornalService.getAll();
        }

        @PostMapping
        public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
           try {
               myEntry.setDate(LocalDateTime.now());
               jornalService.saveEntry(myEntry);
               return new ResponseEntity<>(myEntry,HttpStatus.CREATED);

           }catch (Exception exception){
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
        }

        @GetMapping("id/{myid}")
        public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String myid) {
            Optional<JournalEntry> jornalentri= Optional.ofNullable((jornalService.findById(myid)));
            if(jornalentri.isPresent()){
                return new ResponseEntity<>(jornalentri.get(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

        @DeleteMapping("id/{myid}")
        public ResponseEntity<?> deleteJournalEntryById(@PathVariable String myid) {
           jornalService.deleteById(myid);
           return new ResponseEntity<>(Boolean.TRUE,HttpStatus.NO_CONTENT);
        }

        @PutMapping("/id/{id}")
        public ResponseEntity<JournalEntry> updateById(@PathVariable String id, @RequestBody JournalEntry newEntry) {
            JournalEntry existingEntry = jornalService.findById(id);
            if (existingEntry == null) {
                return ResponseEntity.notFound().build();
            }
            existingEntry.setContent(newEntry.getContent());
            existingEntry.setDate(newEntry.getDate());
            existingEntry.setTitle(newEntry.getTitle());
             jornalService.saveEntry(existingEntry);
           return new ResponseEntity<>(existingEntry,HttpStatus.OK);
        }
    }


