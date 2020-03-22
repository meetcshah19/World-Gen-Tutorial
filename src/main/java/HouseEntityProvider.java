import org.terasology.adventureassets.altarofresurrection.AltarOfResurrectionColliderComponent;
import org.terasology.adventureassets.altarofresurrection.AltarOfResurrectionRootComponent;
import org.terasology.entitySystem.entity.EntityStore;
import org.terasology.logic.location.LocationComponent;
import org.terasology.world.generation.EntityBuffer;
import org.terasology.world.generation.EntityProvider;
import org.terasology.world.generation.Region;

/*
 * Copyright 2020 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class HouseEntityProvider implements EntityProvider {

    @Override
    public void process(Region region, EntityBuffer buffer) {
        HouseFacet houseFacet = region.getFacet(HouseFacet.class);
        for(House house:houseFacet.getHouses()){
            EntityStore entityStore = new EntityStore();
            LocationComponent locationComponent = new LocationComponent(house.loc);
            entityStore.addComponent(locationComponent);
            entityStore.addComponent(new AltarOfResurrectionRootComponent());
            entityStore.addComponent(new AltarOfResurrectionColliderComponent());
            buffer.enqueue(entityStore);

        }
    }
}
