package ro.clockworks.keepers.mock;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.clockworks.keepers.mock.data.ItemCreate;
import ro.clockworks.keepers.mock.data.ItemGet;
import ro.clockworks.keepers.mock.data.ItemQuantityGet;
import ro.clockworks.keepers.mock.data.modify.ItemModify;
import ro.clockworks.keepers.mock.data.modify.ItemQuantityModifyExisting;
import ro.clockworks.keepers.mock.data.modify.ItemQuantityModifyNew;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController("/")
@Slf4j
public class ItemController  {

    private final List<ItemGet> items = new ArrayList<>();

    private final AtomicLong idGenerator = new AtomicLong(31);

    @GetMapping("/items/id")
    List<Long> getIds() {
        return items.stream().map(ItemGet::id).collect(Collectors.toList());
    }

    @GetMapping("/items/bulk")
    List<ItemGet> itemsBulk() {
        return items;
    }

    @GetMapping("/item/{id}")
    ResponseEntity<ItemGet> getItem(@PathVariable Long id) {
        Optional<ItemGet> item = items.stream().filter(i -> i.id() == id).findFirst();
        return item.map(itemGet -> new ResponseEntity<>(itemGet, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/item/{id}")
    ResponseEntity<ItemGet> deleteItem(@PathVariable Long id) {
        Optional<ItemGet> item = items.stream().filter(i -> i.id() == id).findFirst();
        if (item.isPresent()) {
            items.remove(item.get());
            log.info("Item deleted {}", item.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/item")
    ResponseEntity<ItemGet> createItem(@Valid @RequestBody ItemCreate itemCreate) {
        ItemGet item = new ItemGet(idGenerator.incrementAndGet(), itemCreate.name(),
                itemCreate.quantities().stream().map(c -> new ItemQuantityGet(idGenerator.incrementAndGet(), c.quantity(), c.allocated(), c.comments())).toList()
        );
        items.add(item);
        log.info("Created new item {}", item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/item")
    ResponseEntity<ItemGet> modifyItem(@Valid @RequestBody ItemModify itemModify) {
        ItemGet item = items.stream().filter(i -> i.id() == itemModify.id()).findFirst().orElse(null);
        if (item == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        item.name(itemModify.name());
        item.quantities(itemModify.quantities().stream().map(iqmb -> {
                ItemQuantityModifyNew iqm = (ItemQuantityModifyNew) iqmb;
                return new ItemQuantityGet(
                        iqm instanceof ItemQuantityModifyExisting ? ((ItemQuantityModifyExisting) iqm).id() : idGenerator.incrementAndGet(),
                        iqm.quantity(),
                        iqm.allocated(),
                        iqm.comments()

                );
        }).toList());

        log.info("Modified item {}", item);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }





}
