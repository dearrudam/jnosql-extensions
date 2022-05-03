/*
 *  Copyright (c) 2022 Otávio Santana and others
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */
package org.eclipse.jnosql.mapping.mongodb;


import io.smallrye.common.constraint.Assert;
import jakarta.nosql.document.DocumentEntity;
import jakarta.nosql.mapping.document.DocumentEntityConverter;
import org.bson.types.ObjectId;
import org.eclipse.jnosql.mapping.test.CDIExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@CDIExtension
public class DocumentEntityConverterTest {

    @Inject
    private DocumentEntityConverter converter;

    @Test
    public void shouldConverterToDocument() {
        ObjectId id = new ObjectId();
        Music music = new Music(id.toString(), "Music", 2021);
        DocumentEntity entity = converter.toDocument(music);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(Music.class.getSimpleName(), entity.getName());
        Assertions.assertEquals(id, entity.find("_id", ObjectId.class).get());
        Assertions.assertEquals("Music", entity.find("name", String.class).get());
        Assertions.assertEquals(2021, entity.find("year", int.class).get());
    }
}