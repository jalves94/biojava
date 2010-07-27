/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 * Created on Jun 30, 2010
 * Author: Mark 
 *
 */

package org.biojava3.alignment;

import static org.junit.Assert.*;

import org.biojava3.alignment.template.GapPenalty;
import org.biojava3.alignment.template.PairwiseSequenceScorer;
import org.biojava3.alignment.template.SubstitutionMatrix;
import org.biojava3.core.sequence.ProteinSequence;
import org.biojava3.core.sequence.compound.AminoAcidCompound;
import org.junit.Before;
import org.junit.Test;

public class FractionalSimilarityScorerTest {

    private ProteinSequence query, target;
    private GapPenalty gaps;
    private SubstitutionMatrix<AminoAcidCompound> blosum62;
    private NeedlemanWunsch<ProteinSequence, AminoAcidCompound> alignment, self;
    private PairwiseSequenceScorer<ProteinSequence, AminoAcidCompound> scorer1, scorer2;

    @Before
    public void setup() {
        query = new ProteinSequence("ARXB");
        target = new ProteinSequence("RADG");
        gaps = new SimpleGapPenalty((short) 2, (short) 1);
        blosum62 = SubstitutionMatrixHelper.getBlosum62();
        alignment = new NeedlemanWunsch<ProteinSequence, AminoAcidCompound>(query, target, gaps, blosum62);
        self = new NeedlemanWunsch<ProteinSequence, AminoAcidCompound>(query, query, gaps, blosum62);
        scorer1 = new FractionalSimilarityScorer<ProteinSequence, AminoAcidCompound>(alignment);
        scorer2 = new FractionalSimilarityScorer<ProteinSequence, AminoAcidCompound>(self);
    }

    @Test
    public void testFractionalSimilarityScorerPairwiseSequenceAlignerOfSC() {
        assertNotNull(new FractionalSimilarityScorer<ProteinSequence, AminoAcidCompound>(alignment));
    }

    @Test
    public void testFractionalSimilarityScorerSequencePairOfSC() {
        assertNotNull(new FractionalSimilarityScorer<ProteinSequence, AminoAcidCompound>(alignment.getPair()));
    }

    @Test
    public void testGetPair() {
        assertEquals(scorer1.getPair(), alignment.getPair());
        assertEquals(scorer2.getPair(), self.getPair());
    }

    @Test
    public void testGetMaxScore() {
        assertEquals(scorer1.getMaxScore(), 5);
        assertEquals(scorer2.getMaxScore(), 4);
    }

    @Test
    public void testGetMinScore() {
        assertEquals(scorer1.getMinScore(), 0);
        assertEquals(scorer2.getMinScore(), 0);
    }

    @Test
    public void testGetScore() {
        assertEquals(scorer1.getScore(), 2);
        assertEquals(scorer2.getScore(), 3);
    }

}
