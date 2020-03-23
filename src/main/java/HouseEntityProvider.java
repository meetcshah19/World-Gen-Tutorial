import org.terasology.adventureassets.altarofresurrection.AltarOfResurrectionColliderComponent;
import org.terasology.adventureassets.altarofresurrection.AltarOfResurrectionRootComponent;
import org.terasology.assets.management.AssetManager;
import org.terasology.entitySystem.entity.EntityBuilder;
import org.terasology.entitySystem.entity.EntityStore;
import org.terasology.entitySystem.prefab.Prefab;
import org.terasology.entitySystem.prefab.PrefabManager;
import org.terasology.entitySystem.prefab.internal.PojoPrefabManager;
import org.terasology.logic.actions.PlaySoundActionComponent;
import org.terasology.logic.characters.interactions.InteractionTargetComponent;
import org.terasology.logic.location.LocationComponent;
import org.terasology.math.TeraMath;
import org.terasology.math.Vector3fUtil;
import org.terasology.math.geom.Quat4f;
import org.terasology.math.geom.Vector3f;
import org.terasology.math.geom.Vector3i;
import org.terasology.network.NetworkComponent;
import org.terasology.registry.CoreRegistry;
import org.terasology.utilities.Assets;
import org.terasology.world.block.BlockComponent;
import org.terasology.world.block.BlockManager;
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

//            entityStore.addComponent(new InteractionTargetComponent());
//            entityStore.addComponent(new NetworkComponent());

            entityStore.addComponent(new BlockComponent(CoreRegistry.get(BlockManager.class).getBlock("AdventureAssets:AltarOfResurrectionRoot"), new Vector3i(Math.round(house.loc.x),Math.round(house.loc.y),Math.round(house.loc.z))));
            LocationComponent l=new LocationComponent();
            l.replicateChanges=true;
            l.setLocalPosition(new Vector3f(Math.round(house.loc.x),Math.round(house.loc.y),Math.round(house.loc.z)));
            l.setLocalScale(1.0f);
            l.setLocalRotation(new Quat4f(0.0f,0.0f,0.0f,1.0f));
            entityStore.addComponent(l);
            entityStore.addComponent(new AltarOfResurrectionRootComponent());
//            entityStore.addComponent(new AltarOfResurrectionColliderComponent());
            PlaySoundActionComponent playSoundActionComponent = new PlaySoundActionComponent();
            playSoundActionComponent.sounds.add(Assets.getSound("engine:click").get());
            entityStore.addComponent(playSoundActionComponent);

            buffer.enqueue(entityStore);


        }
    }
}
