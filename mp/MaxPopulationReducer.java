import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxPopulationReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

  private Text maxPopulationCountry = new Text();
  private int maxPopulation = Integer.MIN_VALUE;

  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {

    int totalPopulation = 0;
    for (IntWritable value : values) {
      totalPopulation += value.get();
    }

    if (totalPopulation > maxPopulation) {
      maxPopulation = totalPopulation;
      maxPopulationCountry.set(key);
    }
  }

  @Override
  protected void cleanup(Context context) throws IOException, InterruptedException {
    context.write(maxPopulationCountry, new IntWritable(maxPopulation));
  }
}

