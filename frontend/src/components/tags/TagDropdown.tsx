import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import type { ITag } from '../../interfaces/environmentInterfaces';
import AxiosInstance from '../../util/services/AxiosInstance';

const TagDropdown: React.FC<Props> = ({ style, selectedTagId, create }) => {
  const [tags, setTags] = useState<ITag[] | []>([]);
  const { environmentId } = useParams();

  useEffect(() => {
    AxiosInstance.get(`/environments/${environmentId}/tags`)
      .then((response) => setTags(response.data))
      .catch((error) => console.error(error));
  }, []);
  return (
    <div className={`${style}`}>
      <label className="hidden">select a tag:</label>
      <select
        name="tagId"
        className={`${
          create ? '' : 'bg-amber-200'
        } border-2 border-base-100 p-3 rounded-md w-full tracking-widest`}
      >
        <option
          className={`${create ? 'bg-base-300' : 'bg-amber-200'}`}
          value=""
          defaultValue={'Select'}
        >
          select a tag
        </option>
        {tags.length >= 1 &&
          tags.map((tag) => (
            <option
              className={`${create ? 'bg-base-300' : 'bg-amber-200'}`}
              key={tag.id}
              value={tag.id}
              selected={tag.id == selectedTagId}
            >
              {tag.title}
            </option>
          ))}
      </select>
    </div>
  );
};

export default TagDropdown;

interface Props {
  style?: string;
  selectedTagId?: number;
  create?: boolean;
}
