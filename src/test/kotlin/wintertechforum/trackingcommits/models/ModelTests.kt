package wintertechforum.trackingcommits.models

import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.util.AssertionErrors.assertEquals
import wintertechforum.trackingcommits.TrackingcommitsApplication

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [TrackingcommitsApplication::class])
class TrackingcommitsApplicationTests {

    @Autowired
    lateinit var machineImageRepository: MachineImageRepository

    @Test
    fun givenMachineImage_whenSaved_thenFound() {
        val miToSave = MachineImage(0L, MachineImageProvider.AMAZON, "ami-1234567", "us-west-1")
        machineImageRepository.save(miToSave)

        val miFoundMaybe = machineImageRepository.findById(miToSave.id)
        assertTrue(miFoundMaybe.isPresent)

        val miFound = miFoundMaybe.get()

        assertEquals("Saved should match retrieved", miToSave, miFound)
    }

}
