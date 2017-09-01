package com.github.pushmyshop.pushmyshopapi.resources;


import com.github.pushmyshop.pushmyshopapi.models.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "*")
//@RepositoryRestResource(collectionResourceRel = "templates", path = "templates")
public interface Templates extends CrudRepository<Template, Long> {
}


